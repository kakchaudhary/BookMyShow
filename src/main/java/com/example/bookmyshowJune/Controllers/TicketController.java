package com.example.bookmyshowJune.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyshowJune.Dtos.RequestDto.TicketRequestDto;
import com.example.bookmyshowJune.Dtos.ResponseDto.TicketResponseDto;
import com.example.bookmyshowJune.Services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("/book-ticket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){

        try{
            TicketResponseDto response =  ticketService.bookTicket(ticketRequestDto);
            response.setResponseMessage("Ticket booked successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(ticketResponseDto,HttpStatus.BAD_REQUEST);
        }
    }
    
    // For Adding Book Multiple Tickets at One Call By Kaushik 
    @PostMapping("/book-multiple-tickets")
    public String bookTickets(@RequestBody List<TicketRequestDto> ticketRequestDtos) {
        StringBuilder response = new StringBuilder();
        
        for (TicketRequestDto ticketRequestDto : ticketRequestDtos) {
            try {
                // Call service to book the ticket
                TicketResponseDto ticketResponseDto = ticketService.bookTicket(ticketRequestDto);
                ticketResponseDto.setResponseMessage("Ticket booked successfully");
                // Append the response to the StringBuilder
                response.append("User ID: ").append(ticketRequestDto.getUserId())
                        .append(" - Show ID: ").append(ticketRequestDto.getShowId())
                        .append(" - Status: ").append(ticketResponseDto.getResponseMessage())
                        .append("\n");
            } catch (Exception e) {
                // Handle error if booking fails
                TicketResponseDto ticketResponseDto = new TicketResponseDto();
                ticketResponseDto.setResponseMessage(e.getMessage());
                // Append error message to the response
                response.append("User ID: ").append(ticketRequestDto.getUserId())
                        .append(" - Show ID: ").append(ticketRequestDto.getShowId())
                        .append(" - Status: ").append(ticketResponseDto.getResponseMessage())
                        .append("\n");
            }
        }

        return response.toString();
    }


    @DeleteMapping("/{deleteTicket}")
    public String cancelTicket(@PathVariable("deleteTicket") Integer ticketId) throws Exception {

        ticketService.cancelTicket(ticketId);
        return "Ticket has been successfully cancelled";
    }


}

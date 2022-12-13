package com.nighthawk.spring_portfolio.mvc.lightboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/lightboard/")
public class LightBoardAPI {

    private LightBoard lightBoard;
    private JsonNode json;


    @GetMapping("/create/{rows}/{cols}")
    public ResponseEntity<JsonNode> createLightBoard(@PathVariable int rows, @PathVariable int cols) 
    throws JsonMappingException, JsonProcessingException {
        lightBoard = new LightBoard(rows, cols);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(lightBoard.toString());
        json = mapper.readTree(lightBoard.toString());

        return ResponseEntity.ok(json);
    }



    @GetMapping("/lightSetting/{row}/{col}")
    public ResponseEntity<JsonNode> getLight(@PathVariable int row, @PathVariable int col) throws JsonMappingException, JsonProcessingException {
        
      
        ObjectMapper mapper = new ObjectMapper(); 

        if (lightBoard != null) {
            lightBoard.lightSetting(row, col);
            json = mapper.readTree(lightBoard.toString());
        }
        else {
            json = mapper.readTree("{}"); 
        }
        
        return ResponseEntity.ok(json);
    }
    
    @GetMapping("/allOn")
    public ResponseEntity<JsonNode> allOn() throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper(); 

        if (lightBoard != null) {
            lightBoard.allOn();
            json = mapper.readTree(lightBoard.toString()); 
        }
        else {
            json = mapper.readTree("{}"); 
        }
        
        return ResponseEntity.ok(json);
    }
}
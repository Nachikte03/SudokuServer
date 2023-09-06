package controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.SudokuService;

@RestController
public class SudokuController {

    SudokuService sudokuService = new SudokuService();
    @GetMapping("/getBoard/{level}")
    @CrossOrigin
    public ResponseEntity<JSONObject> getBoard(@PathVariable String level){
        int[][] result = sudokuService.getBoard(level);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("board",result);
        return new ResponseEntity<>(jsonObject, responseHeaders,HttpStatusCode.valueOf(200));
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webservice.apiws.ws.jajanan;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import webservice.apiws.ws.jajanan.database.DataMhs;
import webservice.apiws.ws.jajanan.database.DataMhsJpaController;
import webservice.apiws.ws.jajanan.database.exceptions.NonexistentEntityException;

/**
 *
 * @author HP
 */
@RestController //gabugnan response body dan controller
public class dataController {
    DataMhsJpaController controller = new DataMhsJpaController();
    
    //GET DATA
    @GetMapping(value = "/get")
    public List<DataMhs> getData(){
        List<DataMhs> data = new ArrayList<>();
        try{
            data = controller.findDataMhsEntities();
        }catch (Exception e) {
            
        }
        return controller.findDataMhsEntities();
    }
    
    //delete data
    @ResponseBody
    @DeleteMapping(value = "/del/{nim}")
    public void deleteData(@PathVariable String nim) throws NonexistentEntityException{
        controller.destroy(nim);
    }
    
    //create
    @ResponseBody
    @PostMapping(value = "/cre")
    public void createData(@RequestBody DataMhs dataMhs) throws Exception{
        controller.create(dataMhs);
    
    }
    
    //update
    @ResponseBody
    @PutMapping(value = "/upd/{nim}")
    public void updateData(@RequestBody DataMhs dataMhs) throws Exception{
        controller.edit(dataMhs);
    }
}

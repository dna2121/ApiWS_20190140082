/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webservice.apiws.ws.jajanan;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    //GET DATA
    @GetMapping(value = "/get")
    public List<DataMhs> getData(){
        List<DataMhs> data = new ArrayList<>();
        DataMhsJpaController controller = new DataMhsJpaController();
        
        try{
            data = controller.findDataMhsEntities();
        }catch (Exception e) {
            
        }
        return controller.findDataMhsEntities();
    }
    
    
    @ResponseBody
    @DeleteMapping(value = "/del/{nim}")
    public void deleteData(@PathVariable String nim){
        DataMhsJpaController controller = new DataMhsJpaController();
        
        try {
            controller.destroy(nim);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(dataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
//    @ResponseBody
//    @PostMapping(value = "/cre")
//    public void createData(@RequestBody DataMhs dataMhs) throws Exception{
//        DataMhsJpaController controller = new DataMhsJpaController();
//        
//        controller.create(dataMhs);
//    
//    }
//    
//    
    
}

package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.apppcmarket.entity.Attachment;
import uz.pdp.apppcmarket.payLoad.Result;
import uz.pdp.apppcmarket.service.AttachmentService;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    //Create
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadFile(request);
        return result;
    }

    //Read
    @GetMapping
    public Page<Attachment> getAttachment(@RequestParam Integer page){
        return attachmentService.getAttachment(page);
    }

    //Read by id
    @GetMapping("/{id}")
    public Attachment getAttachmentById(@PathVariable Integer id){
        return attachmentService.getAttachmentById(id);
    }

    //Update
    @PutMapping("/{id}")
    public Result updateAttachment(@PathVariable Integer id, MultipartHttpServletRequest request){
        return attachmentService.updateAttachment(id,request);
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }

}

package com.project.attendance.controller;

 import com.project.attendance.dao.StreamDAO;
 import com.project.attendance.model.Stream;
 import com.project.attendance.util.StreamCSV;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 import javax.servlet.http.HttpServletRequest;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.List;

@Controller
public class StreamController {
    @Autowired
    private StreamDAO streamDAO;

    @RequestMapping("/stream")
    public String viewHomePage(Model model){
        List<Stream> streamDetails = streamDAO.findAll();
        model.addAttribute("streamDetails", streamDetails);
        return "stream";
    }

    @RequestMapping("/stream/new")
    public String addStream(Model model){
        Stream stream =new Stream();
        model.addAttribute("stream", stream);
        return "addStream";
    }

    @RequestMapping(value="/stream/save",method= RequestMethod.POST)
    public String saveStream(@ModelAttribute("stream") Stream stream){
        streamDAO.save(stream);
        return  "redirect:/stream";
    }

    @RequestMapping("/stream/edit/{id}")
    public ModelAndView updateCourse(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateStream"));

        Stream stream = streamDAO.findById(id);
        mav.addObject("stream", stream);
        return  mav;
    }

    @RequestMapping("/stream/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        streamDAO.delete(id);
        return  "redirect:/stream";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/stream/upload") // //new annotation since 4.3
    public String StreamCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/stream/csv";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            request.setAttribute("path", path);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "forward:/stream/saveAll";
    }



    @RequestMapping(value="/stream/saveAll",method= RequestMethod.POST)
    public String saveAllStreams(@ModelAttribute("stream") Stream stream, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        StreamCSV csv=new StreamCSV();
        Path[] args={path} ;
        List<Stream> list = StreamCSV.main(args);
        try {
            streamDAO.saveAll(list);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "redirect:/stream";
    }

    @RequestMapping("/stream/csv")
    public String view(Model model){
        List<Stream> streamDetails = streamDAO.findAll();
        model.addAttribute("streamDetails", streamDetails);
        return "addAllStreams";
    }
}

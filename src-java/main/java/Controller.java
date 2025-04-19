

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    @Qualifier("albumService")
    private AlbumService albumService;
    //private Service albumService;

    @GetMapping("/albums")
    public String albums() {
        return albumService.getAlbumList();
    }

}
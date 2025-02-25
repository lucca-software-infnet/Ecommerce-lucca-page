package EcommercePage.producingwebservice.model.Controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    private final String diretorioImagens = "src/main/java/EcommercePage/producingwebservice/model/static/imageProduto/";

    @GetMapping("/{nomeImagem}")
    public ResponseEntity<Resource> obterImagem(@PathVariable String nomeImagem) throws MalformedURLException {
        Path caminhoImagem = Paths.get(diretorioImagens).resolve(nomeImagem);
        Resource recurso = new UrlResource(caminhoImagem.toUri());

        if (recurso.exists() || recurso.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
                    .body(recurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

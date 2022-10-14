package pl.adamsiedlecki.jabcokeum.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adamsiedlecki.jabcokeum.openapi.server.api.JabcokeumApi;
import pl.adamsiedlecki.jabcokeum.openapi.server.model.BlockHeightResponse;
import pl.adamsiedlecki.jabcokeum.service.LocalBlockchainService;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CurrentBlockHeightController implements JabcokeumApi {

    private final LocalBlockchainService localBlockchainService;

    @Override
    public ResponseEntity<BlockHeightResponse> getHeightOfBlockchain() {
        var response = new BlockHeightResponse();
        response.setBlockHeight(localBlockchainService.getHeight().longValue());
        return ResponseEntity.ok(response);
    }
}

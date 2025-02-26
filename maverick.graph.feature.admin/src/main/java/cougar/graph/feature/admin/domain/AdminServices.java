package cougar.graph.feature.admin.domain;

import cougar.graph.model.security.Authorities;
import cougar.graph.store.EntityStore;
import cougar.graph.store.RepositoryType;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j(topic = "graph.feature.admin.domain")
public class AdminServices {

    private final EntityStore graph;

    public AdminServices(EntityStore graph) {
        this.graph = graph;
    }


    public Mono<Void> reset(Authentication authentication, RepositoryType repositoryType) {
        return this.graph.reset(authentication, repositoryType, Authorities.APPLICATION)
                .doOnSubscribe(sub -> log.info("Purging repository through admin services"));
    }

    public Mono<Void> importEntities(Publisher<DataBuffer> bytes, String mimetype, Authentication authentication) {
        return this.graph.importStatements(bytes, mimetype, authentication, Authorities.APPLICATION)
                .doOnSubscribe(sub -> log.info("Importing statements of type '{}' through admin services", mimetype));
    }
}

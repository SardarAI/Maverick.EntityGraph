package utils;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.jsonld.JSONLDWriterFactory;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class TestRepository implements AutoCloseable{

    private final Repository repository; 

    public TestRepository() {

        repository = new SailRepository(new MemoryStore());
    }

    public void load(Resource resource) throws IOException {
        try (RepositoryConnection conn = this.repository.getConnection()) {
            try (InputStream input = resource.getInputStream()) {
                // add the RDF data from the inputstream directly to our database
                conn.add(input, RDFFormat.TURTLE);
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public String dump(RDFFormat format) {
        try (RepositoryConnection conn = this.repository.getConnection()) {
            StringWriter sw = new StringWriter();

            RDFWriter writer = Rio.createWriter(format, sw);
            conn.export(writer);
            return sw.toString();
        }
    }

    @Override
    public void close()  {
        this.repository.shutDown();
    }
}

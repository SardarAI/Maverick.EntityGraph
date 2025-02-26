package cougar.graph.store.rdf.helpers;

import cougar.graph.store.rdf.models.Incoming;
import org.eclipse.rdf4j.common.exception.RDF4JException;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.repository.util.AbstractRDFInserter;

public class TriplesCollector extends AbstractRDFInserter {
        private final Incoming model;

        public TriplesCollector() {
            super(SimpleValueFactory.getInstance());
            this.model = new Incoming();
        }


        public Incoming getModel() {
            return model;
        }

        @Override
        protected void addNamespace(String prefix, String name) throws RDF4JException {
            this.model.getBuilder().setNamespace(prefix, name);
        }

        @Override
        protected void addStatement(Resource subj, IRI pred, Value obj, Resource ctxt) throws RDF4JException {
            this.model.getBuilder().add(subj, pred, obj);

        }
    }
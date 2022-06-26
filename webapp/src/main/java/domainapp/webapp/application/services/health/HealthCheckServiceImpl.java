package domainapp.webapp.application.services.health;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.isis.applib.services.health.Health;
import org.apache.isis.applib.services.health.HealthCheckService;

import domainapp.modules.simple.dom.afiliados.Afiliados;

@Service
@Named("domainapp.HealthCheckServiceImpl")
public class HealthCheckServiceImpl implements HealthCheckService {

    private final Afiliados afiliados;

    @Inject
    public HealthCheckServiceImpl(Afiliados afiliados) {
        this.afiliados = afiliados;
    }

    @Override
    public Health check() {
        try {
            afiliados.ping();
            return Health.ok();
        } catch (Exception ex) {
            return Health.error(ex);
        }
    }
}

package es.ctic.mail;

import es.ctic.mail.sim.MailSimProvider;
import es.ctic.mail.smtp.MailSmtpProvider;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Clase para probar la Factoria de los servicios de envio de mail
 */
public class MailProviderFactoryTest {

    @Test
    public void getMailSmtpProvider() {
        final MailProvider provider = MailProviderFactory.getMailProvider("smtp");
        Assert.assertEquals(MailSmtpProvider.class, provider.getClass());
    }

    @Test
    public void getMailSimProvider() {
        final MailProvider provider = MailProviderFactory.getMailProvider("sim");
        Assert.assertEquals(MailSimProvider.class, provider.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInvalidMailProvider() {
        MailProviderFactory.getMailProvider("non_existing_provider");
    }

}
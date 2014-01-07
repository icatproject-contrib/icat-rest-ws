/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "instruments")
public class InvestigationInstrumentConverter {

  private static Logger log = Logger.getLogger(InvestigationInstrumentConverter.class);

  @XmlElement
  private Collection<String> instrument;

  public InvestigationInstrumentConverter() {
  }

  public InvestigationInstrumentConverter(Collection<String> list) {
    this.instrument = list;
  }
}

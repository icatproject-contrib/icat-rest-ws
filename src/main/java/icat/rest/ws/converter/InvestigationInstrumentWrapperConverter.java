/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "instruments")
public class InvestigationInstrumentWrapperConverter {

  private static Logger log = Logger.getLogger(InvestigationInstrumentWrapperConverter.class);
  @XmlElement(name = "instrument")
  private List<String> instruments;

  public InvestigationInstrumentWrapperConverter() {
  }

  public InvestigationInstrumentWrapperConverter(List<String> list) {
    this.instruments = list;
  }
}

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
@XmlRootElement(name = "proposals")
public class InvestigationProposalWrapperConverter {

  private static Logger log = Logger.getLogger(InvestigationProposalWrapperConverter.class);
  @XmlElement(name = "proposal")
  private List<String> proposals;

  public InvestigationProposalWrapperConverter() {
  }

  public InvestigationProposalWrapperConverter(List<String> list) {
    this.proposals = list;
  }
}

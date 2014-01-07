/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;


/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class ProposalConverter {

  private static Logger log = Logger.getLogger(ProposalConverter.class);

  @XmlElement
  private ArrayList<String> proposal;

  public ProposalConverter() {
  }

  public ProposalConverter(ArrayList<String> list) {
    proposal = list;
  }
  
}

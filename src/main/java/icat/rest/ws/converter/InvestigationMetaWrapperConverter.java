/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class InvestigationMetaWrapperConverter {

  @XmlElement(name = "proposal")
  private List<InvestigationMetaConverter> proposals = new ArrayList<InvestigationMetaConverter>();
  private static Logger log = Logger.getLogger(InvestigationMetaWrapperConverter.class);

  public InvestigationMetaWrapperConverter() {
  }

  public InvestigationMetaWrapperConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationMetaConverter> list = new ArrayList<InvestigationMetaConverter>();

    for (int i = 0; i < invList.size(); i++) {
      InvestigationMetaConverter invConvertor = new InvestigationMetaConverter(invList.get(i));
      list.add(invConvertor);
    }

    this.proposals = list;
  }
}
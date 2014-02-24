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
public class InvestigationAllWrapperConverter {

  @XmlElement(name = "proposal")
  private List<InvestigationAllConverter> proposals = new ArrayList<InvestigationAllConverter>();
  private static Logger log = Logger.getLogger(InvestigationMetaWrapperConverter.class);

  public InvestigationAllWrapperConverter() {
  }

  public InvestigationAllWrapperConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationAllConverter> list = new ArrayList<InvestigationAllConverter>();

    for (int i = 0; i < invList.size(); i++) {
      InvestigationAllConverter invConvertor = new InvestigationAllConverter(invList.get(i));
      list.add(invConvertor);
    }

    this.proposals = list;
  }
}
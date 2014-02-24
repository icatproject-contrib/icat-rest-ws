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
public class InvestigationDatasetAllWrapperConverter {

  @XmlElement(name = "proposal")
  private List<InvestigationDatasetAllConverter> proposals = new ArrayList<InvestigationDatasetAllConverter>();
  private static Logger log = Logger.getLogger(InvestigationMetaWrapperConverter.class);

  public InvestigationDatasetAllWrapperConverter() {
  }

  public InvestigationDatasetAllWrapperConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationDatasetAllConverter> list = new ArrayList<InvestigationDatasetAllConverter>();

    for (int i = 0; i < invList.size(); i++) {
      InvestigationDatasetAllConverter invConvertor = new InvestigationDatasetAllConverter(invList.get(i));
      list.add(invConvertor);
    }

    this.proposals = list;
  }
}
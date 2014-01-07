/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class InvestigationAllConverter {

  @XmlElement
  private ArrayList<InvestigationDatasetMetaConverter> proposal;
  private static Logger log = Logger.getLogger(InvestigationAllConverter.class);

  public InvestigationAllConverter() {
  }

  public InvestigationAllConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationDatasetMetaConverter> list = new ArrayList<InvestigationDatasetMetaConverter>();

    for (int i=0; i<invList.size(); i++) {
      InvestigationDatasetMetaConverter invConvertor = new InvestigationDatasetMetaConverter(invList.get(i));
      list.add(invConvertor);
    }
    
    Collections.sort(list);
    
    this.proposal = list;
  }
}

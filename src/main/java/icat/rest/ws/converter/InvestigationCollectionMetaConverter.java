/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class InvestigationCollectionMetaConverter {

  @XmlElement
  private ArrayList<InvestigationDatasetMetaConverter> proposal;
  private static Logger log = Logger.getLogger(InvestigationCollectionMetaConverter.class);

  public InvestigationCollectionMetaConverter() {
  }

  public InvestigationCollectionMetaConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationDatasetMetaConverter> list = new ArrayList<InvestigationDatasetMetaConverter>();

    for (int i=0; i<invList.size(); i++) {
      InvestigationDatasetMetaConverter invConvertor = new InvestigationDatasetMetaConverter(invList.get(i));
      list.add(invConvertor);
    }
    this.proposal = list;
  }
}

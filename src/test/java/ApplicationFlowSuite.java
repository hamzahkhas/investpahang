import org.junit.*;

import Company.UploadInvoice;
import Executive.GenerateLetter;
import UnitKewangan.ApproveInvoiceFirstLevel;
import UnitKewangan.ApproveInvoiceSecondLevel;
import UnitProcess.ApproveFirstLayer;
import UnitProcess.ApproveSecondLayer;

public class ApplicationFlowSuite {

    @RunWith(Suite.class)
    @Suite.SuiteClasses({
        ApproveFirstLayer.java,
        ApproveSecondLayer.java,
        UploadInvoice.java,
        ApproveInvoiceFirstLevel.java,
        ApproveInvoiceSecondLevel.java,
        GenerateLetter.java
    })

    public class ApplicationFlowSuite {
        
    }
    
}

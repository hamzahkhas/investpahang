import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import Company.UploadInvoice;
import Executive.GenerateLetter;
import UnitKewangan.ApproveInvoiceFirstLevel;
import UnitKewangan.ApproveInvoiceSecondLevel;
import UnitProcess.ApproveFirstLayer;
import UnitProcess.ApproveSecondLayer;



@Suite
@SelectClasses({
    ApproveFirstLayer.class,
    ApproveSecondLayer.class,
    UploadInvoice.class,
    ApproveInvoiceFirstLevel.class,
    ApproveInvoiceSecondLevel.class,
    GenerateLetter.class
})

public class ApplicationFlowSuite {
    
}
    


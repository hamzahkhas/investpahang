import org.junit.jupiter.api.Test;

import Company.CreateApplication;
import Company.UploadInvoice;
import Executive.GenerateLetter;
import UnitKewangan.ApproveInvoiceFirstLevel;
import UnitKewangan.ApproveInvoiceSecondLevel;
import UnitProcess.ApproveFirstLayer;
import UnitProcess.ApproveSecondLayer;

public class MainFlow {

    @Test
    public void createApp() {
        CreateApplication createApplication = new CreateApplication();
        createApplication.testCreateApplication();
    }
    
    @Test
    public void runMainFlow() {

        String fileNo = "SUK.PHG/UPEN.002/8.08.2467";
        int applicationNo = 356;

        ApproveFirstLayer approveFirstLayer = new ApproveFirstLayer();
        ApproveSecondLayer approveSecondLayer = new ApproveSecondLayer();
        UploadInvoice uploadInvoice = new UploadInvoice();
        ApproveInvoiceFirstLevel approveInvoiceFirstLevel = new ApproveInvoiceFirstLevel();
        ApproveInvoiceSecondLevel approveInvoiceSecondLevel = new ApproveInvoiceSecondLevel();
        GenerateLetter generateLetter = new GenerateLetter();

        approveFirstLayer.testApproveFirstLayer(applicationNo);
        approveSecondLayer.testApproveSecondLayer(applicationNo);
        uploadInvoice.testUploadInvoice(applicationNo);
        approveInvoiceFirstLevel.testApproveInvoiceFirstLevel(applicationNo);
        approveInvoiceSecondLevel.testApproveInvoiceSecondLevel(applicationNo);
        generateLetter.testGenerateLetter(applicationNo, fileNo);

    }
}

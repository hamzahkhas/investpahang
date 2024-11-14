import org.junit.jupiter.api.Test;

import Company.CreateApplication;
import Company.UploadInvoice;
import Executive.ApproveProject;
import Executive.AssignReviewer;
import Executive.GenerateLetter;
import Executive.MoveToJKT;
import Executive.MoveToMMK;
import Executive.SendToPemantauan;
import UnitKewangan.ApproveInvoiceFirstLevel;
import UnitKewangan.ApproveInvoiceSecondLevel;
import UnitProcess.ApproveFirstLayer;
import UnitProcess.ApproveSecondLayer;
    
public class MainFlow {

    @Test
    public void testing() {
        SendToPemantauan sendToPemantauan = new SendToPemantauan();
        sendToPemantauan.testSendToPemantauan(1170);
    }

    @Test
    public void createApp() {

        for (int i = 0; i < 10; i++ ) {

            CreateApplication createApplication = new CreateApplication();
            createApplication.testCreateApplication();

        }
        
    }
    
    @Test
    public void runMainFlow() {

        for (int i = 2035; i < 2036; i++) {

            String fileNo = "Test.08."+i;
            int applicationNo = i;
    
            ApproveFirstLayer approveFirstLayer = new ApproveFirstLayer();
            ApproveSecondLayer approveSecondLayer = new ApproveSecondLayer();
            UploadInvoice uploadInvoice = new UploadInvoice();
            ApproveInvoiceFirstLevel approveInvoiceFirstLevel = new ApproveInvoiceFirstLevel();
            ApproveInvoiceSecondLevel approveInvoiceSecondLevel = new ApproveInvoiceSecondLevel();
            GenerateLetter generateLetter = new GenerateLetter();
            AssignReviewer assignReviewer = new AssignReviewer();
            MoveToJKT moveToJKT = new MoveToJKT();
            MoveToMMK moveToMMK = new MoveToMMK();
            ApproveProject approveProject = new ApproveProject();
    
    
    
            approveFirstLayer.testApproveFirstLayer(applicationNo);
            approveSecondLayer.testApproveSecondLayer(applicationNo);
            uploadInvoice.testUploadInvoice(applicationNo);
            approveInvoiceFirstLevel.testApproveInvoiceFirstLevel(applicationNo);
            approveInvoiceSecondLevel.testApproveInvoiceSecondLevel(applicationNo);
            generateLetter.testGenerateLetter(applicationNo, fileNo);
            // moveToJKT.testMoveToJKT(applicationNo);
            // moveToMMK.testMoveToMMK(applicationNo);
            // approveProject.testApproveProject(applicationNo);
        }


    }
}

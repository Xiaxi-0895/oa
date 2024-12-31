import org.example.imooc_oa.Entity.LeaveForm;
import org.example.imooc_oa.mapper.LeaveFormMapper;

import java.util.Date;

public class test {
    public static void main(String[] args) {
        LeaveFormMapper mapper = new LeaveFormMapper();
        LeaveForm leaveForm = mapper.selectLeaveForm(34);
        Date day1 = leaveForm.getStartTime();
        Date day2 = leaveForm.getEndTime();
        System.out.println((day2.getTime() - day1.getTime())/(1000*60*60*24)+1 );
    }
}

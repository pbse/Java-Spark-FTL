import Model.Model;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import org.junit.Test;

/**
 *
 * @author prash_000
 */
public class UpdateUserTest {
    
    public UpdateUserTest() {
    }

    @Test
    public void aUserIsCorrectlyUpdated() {
        
        Model model = EasyMock.createMock(Model.class);
        expect(model.updateUser("T13", "Test Name","Mid Name","Test",20,'M',"1234567891",12 
        )).andReturn(1);
    }
    
    @Test
    public void aUserIsNotFound() {
        
        Model model = EasyMock.createMock(Model.class);
        expect(model.updateUser("T14", "Test Name","Mid Name","Test",20,'M',"1234567891",12 
        )).andReturn(0);
    }
}

package database;

import employee.Employee;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import register_entry.RegisterEntry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import register_entry.RegisterEntryNull;

import java.lang.reflect.Field;
import java.util.HashMap;

// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(RegistrationDB.class)
public class RegistrationDB_UTest
{
    public RegistrationDB_UTest()
    {

    }

    @Before
    public void initialize()
    {

    }

    @Test
    @SuppressWarnings("unchecked")
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = RegistrationDB.class.getDeclaredField("db");
        field.setAccessible(true);

        Database registrationDB_underTest = new RegistrationDB();
        HashMap<Employee, RegisterEntry> mock_db = (HashMap<Employee, RegisterEntry>) Mockito.mock(HashMap.class);
        field.set(registrationDB_underTest, mock_db);

        Employee mockEmployee = Mockito.mock(Employee.class);
        RegisterEntry mockEntry = Mockito.mock(RegisterEntry.class);

        registrationDB_underTest.addEntry(mockEmployee, mockEntry);
        Mockito.verify(mock_db, Mockito.times(1)).put(mockEmployee, mockEntry);
    }

    @Test
    public void t_getEntry_NoDefault() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = RegistrationDB.class.getDeclaredField("db");
        field.setAccessible(true);

        Database registrationDB_underTest = new RegistrationDB();
        HashMap<Employee, RegisterEntry> mock_db = new HashMap<>();
        field.set(registrationDB_underTest, mock_db);

        Employee mockEmployee = Mockito.mock(Employee.class);
        RegisterEntry mockRegisterEntry = Mockito.mock(RegisterEntry.class);
        mock_db.put(mockEmployee, mockRegisterEntry);

        RegisterEntry returnedEntry = registrationDB_underTest.getEntry(mockEmployee);
        Assert.assertEquals("Testing getEntry - should return mockObject", mockRegisterEntry, returnedEntry);
    }

    @Test
    public void t_getEntry_Default() throws Exception
    {
        Field field = RegistrationDB.class.getDeclaredField("db");
        field.setAccessible(true);

        Database registrationDB_underTest = new RegistrationDB();
        HashMap<Employee, RegisterEntry> mock_db = new HashMap<>();
        field.set(registrationDB_underTest, mock_db);

        Employee mockEmployee = Mockito.mock(Employee.class);
        // Make sure the constructor for RegisterEntryNull is being mocked
        RegisterEntryNull mockRegisterEntry = Mockito.mock(RegisterEntryNull.class);
        PowerMockito.whenNew(RegisterEntryNull.class).withNoArguments().thenReturn(mockRegisterEntry);

        RegisterEntry returnedEntry = registrationDB_underTest.getEntry(mockEmployee);
        Assert.assertEquals("Testing getEntry - should return mockObject", mockRegisterEntry, returnedEntry);
    }
}

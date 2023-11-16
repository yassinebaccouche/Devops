package tn.spring.devops_project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {

    @Mock
    OperatorRepository operatorRepository;

    @InjectMocks
    OperatorServiceImpl operatorService;

    @Test
    void retrieveAllOperators() {
        // Arrange
        Operator operator1 = new Operator(1L, "John", "Doe", "password", null);
        Operator operator2 = new Operator(2L, "Jane", "Doe", "password", null);
        List<Operator> operatorList = Arrays.asList(operator1, operator2);

        when(operatorRepository.findAll()).thenReturn(operatorList);

        // Act
        List<Operator> result = operatorService.retrieveAllOperators();

        // Assert
        assertEquals(operatorList.size(), result.size());
        verify(operatorRepository, times(1)).findAll();
    }

    @Test
    void addOperator() {
        // Arrange
        Operator operatorToAdd = new Operator(null, "John", "Doe", "password", null);
        Operator savedOperator = new Operator(1L, "John", "Doe", "password", null);

        when(operatorRepository.save(operatorToAdd)).thenReturn(savedOperator);

        // Act
        Operator result = operatorService.addOperator(operatorToAdd);

        // Assert
        assertEquals(savedOperator, result);
        verify(operatorRepository, times(1)).save(operatorToAdd);
    }

    @Test
    void deleteOperator() {
        // Arrange
        Long operatorIdToDelete = 1L;

        // Act
        operatorService.deleteOperator(operatorIdToDelete);

        // Assert
        verify(operatorRepository, times(1)).deleteById(operatorIdToDelete);
    }

    @Test
    void updateOperator() {
        // Arrange
        Operator operatorToUpdate = new Operator(1L, "John", "Doe", "password", null);

        when(operatorRepository.save(operatorToUpdate)).thenReturn(operatorToUpdate);

        // Act
        Operator result = operatorService.updateOperator(operatorToUpdate);

        // Assert
        assertEquals(operatorToUpdate, result);
        verify(operatorRepository, times(1)).save(operatorToUpdate);
    }

    @Test
    void retrieveOperator() {
        // Arrange
        Long operatorId = 1L;
        Operator operator = new Operator(operatorId, "John", "Doe", "password", null);

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        // Act
        Operator result = operatorService.retrieveOperator(operatorId);

        // Assert
        assertEquals(operator, result);
        verify(operatorRepository, times(1)).findById(operatorId);
    }
}

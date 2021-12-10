package com.example.time.demo.demo.service;

import com.example.time.demo.demo.exception.TimeNotFoundException;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.model.CustomTimes;
import com.example.time.demo.demo.repository.RepositoryTime;
import com.example.time.demo.demo.util.ListCustomTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {
    @Mock
    RepositoryTime repositoryTime;
    @InjectMocks
    TimeServiceImpl timeService;

    List<CustomTime> customTimeListMock = new ArrayList<>();

    @BeforeEach
    public void createListCustomTime() {
        customTimeListMock = ListCustomTime.getListCustomTime();
    }

    @Test
    public void returnListCustomTimeTest() {
        when(repositoryTime.findAll()).thenReturn(customTimeListMock);
        assertEquals(customTimeListMock, timeService.getAll());
    }

    @Test
    public void getByIdCustomTimeTest() {
        when(repositoryTime.findById(0L)).thenReturn(Optional.ofNullable(customTimeListMock.get(0)));
        when(repositoryTime.findById(1L)).thenReturn(Optional.ofNullable(customTimeListMock.get(1)));
        assertEquals(customTimeListMock.get(0), timeService.findById(0));
        assertEquals(customTimeListMock.get(1), timeService.findById(1));
        verify(repositoryTime, times(2)).findById(anyLong()); //checking the number of attempts
    }

    @Test
    public void ifIdMore500CustomTimeTest() {
        assertEquals(CustomTimes.getSimpleInsnace(), timeService.findById(501));
    }

    @Test
    public void ifIdMore100CustomTimeTest() {
        assertThrows(IllegalArgumentException.class, () -> timeService.findById(101));
    }

    @Test
    public void ifValueNotFoundByIdCustomTimeTest() {
        when(repositoryTime.findById(5L)).thenThrow(new TimeNotFoundException("this time not found"));
        assertThrows(TimeNotFoundException.class, () -> timeService.findById(5));
    }

    @Test
    public void saveCustomTimeTest() {
        CustomTime customTime = ListCustomTime.getListCustomTime().get(2);
        when(repositoryTime.save(any())).thenReturn(customTime);
        assertEquals(customTime, timeService.save(customTime));
    }

}
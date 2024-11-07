package com.example.androidassignments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainActivityTest {

    @Mock
    private MainActivity mainActivity;

    @Mock
    private Bundle mockBundle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainActivity = spy(new MainActivity());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testOnCreate() {
        doNothing().when(mainActivity).setContentView(anyInt());

        mainActivity.onCreate(mockBundle);

        verify(mainActivity).setContentView(anyInt());
    }

    @Test
    public void testOnActivityResult() {
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getStringExtra("Response")).thenReturn("Test Passed");

        mainActivity.onActivityResult(10, Activity.RESULT_OK, mockIntent);

        verify(mockIntent).getStringExtra("Response");
    }

    @Test
    public void testOnStart() {
        mainActivity.onStart();
        verify(mainActivity).onStart();
    }

    @Test
    public void testOnResume() {
        mainActivity.onResume();
        verify(mainActivity).onResume();
    }

    @Test
    public void testOnPause() {
        mainActivity.onPause();
        verify(mainActivity).onPause();
    }

    @Test
    public void testOnStop() {
        mainActivity.onStop();
        verify(mainActivity).onStop();
    }

    @Test
    public void testOnDestroy() {
        mainActivity.onDestroy();
        verify(mainActivity).onDestroy();
    }

    @Test
    public void testOnSaveInstanceState() {
        mainActivity.onSaveInstanceState(mockBundle);
        verify(mainActivity).onSaveInstanceState(mockBundle);
    }

    @Test
    public void testOnRestoreInstanceState() {
        mainActivity.onRestoreInstanceState(mockBundle);
        verify(mainActivity).onRestoreInstanceState(mockBundle);
    }
}

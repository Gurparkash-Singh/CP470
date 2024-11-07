package com.example.androidassignments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ListItemsActivityTest {

    @Mock
    private ListItemsActivity listItemsActivity;

    @Mock
    private Bundle mockBundle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        listItemsActivity = spy(new ListItemsActivity());
    }

    @After
    public void tearDown() throws Exception {
        listItemsActivity = null;
    }

    @Test
    public void testOnCreate() {
        doNothing().when(listItemsActivity).setContentView(anyInt());

        listItemsActivity.onCreate(mockBundle);

        verify(listItemsActivity).setContentView(anyInt());
    }

    @Test
    public void testOnActivityResult() {
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getExtras()).thenReturn(null);

        listItemsActivity.onActivityResult(1, Activity.RESULT_OK, mockIntent);

        verify(listItemsActivity).onActivityResult(1, Activity.RESULT_OK, mockIntent);
    }

    @Test
    public void testOnRequestPermissionsResult() {
        String[] permissions = {Manifest.permission.CAMERA};
        int[] grantResults = {PackageManager.PERMISSION_GRANTED};

        listItemsActivity.onRequestPermissionsResult(100, permissions, grantResults);

        verify(listItemsActivity).onRequestPermissionsResult(100, permissions, grantResults);
    }

    @Test
    public void testOnStart() {
        listItemsActivity.onStart();
        verify(listItemsActivity).onStart();
    }

    @Test
    public void testOnResume() {
        listItemsActivity.onResume();
        verify(listItemsActivity).onResume();
    }

    @Test
    public void testOnPause() {
        listItemsActivity.onPause();
        verify(listItemsActivity).onPause();
    }

    @Test
    public void testOnStop() {
        listItemsActivity.onStop();
        verify(listItemsActivity).onStop();
    }

    @Test
    public void testOnDestroy() {
        listItemsActivity.onDestroy();
        verify(listItemsActivity).onDestroy();
    }

    @Test
    public void testOnSaveInstanceState() {
        listItemsActivity.onSaveInstanceState(mockBundle);
        verify(listItemsActivity).onSaveInstanceState(mockBundle);
    }

    @Test
    public void testOnRestoreInstanceState() {
        listItemsActivity.onRestoreInstanceState(mockBundle);
        verify(listItemsActivity).onRestoreInstanceState(mockBundle);
    }
}

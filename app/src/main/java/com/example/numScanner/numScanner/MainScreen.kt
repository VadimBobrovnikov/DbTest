@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.numScanner.numScanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.DatabaseViewActivity
import com.example.myapplication.MainDB
import com.example.numScanner.numScanner.Camera.CameraScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.example.numScanner.numScanner.noPremission.NoPermissionScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier

//запрос разрешения на использование камеры
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(db: MainDB, activity: AppCompatActivity) {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        db = db,
        activity = activity

    )
}
//вызов одного из двух экранов, либо камера, либо отсутствие разрешения
@Composable
private fun MainContent(
    hasPermission: Boolean,
    db: MainDB,
    activity: AppCompatActivity,
    onRequestPermission: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
    if (hasPermission) {
        CameraScreen(db = db)
    } else {
        NoPermissionScreen(onRequestPermission)
    }
    Button(
        onClick = {
            activity.startActivity(Intent(activity, DatabaseViewActivity::class.java))
        },
        modifier = Modifier.align(Alignment.BottomEnd)
    ) {
        Text("View Database")
    }
}}

@Preview
@Composable
private fun Preview_MainContent() {
    val fakeDb = MainDB.getDb(LocalContext.current)
    val fakeActivity = AppCompatActivity() // You may replace this with your actual implementation
    MainContent(
        hasPermission = true,
        db = fakeDb,
        activity = fakeActivity,
        onRequestPermission = {}
    )
}
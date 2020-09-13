package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.DriveRequest
import com.allianz.open.fasttrackexaminant.dto.DriveResponse
import com.allianz.open.fasttrackexaminant.service.DriveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DriveController {

    @Autowired
    private lateinit var driveService: DriveService

    @PostMapping("/Drive")
    fun schedule(@RequestBody driveRequest: DriveRequest):DriveResponse = driveService.schedule(driveRequest)
}
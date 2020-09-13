package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Drive
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DriveRepository : CrudRepository<Drive,Int> {
}
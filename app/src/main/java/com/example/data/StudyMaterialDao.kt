package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyMaterialDao {
    @Query("SELECT * FROM study_materials ORDER BY createdAt DESC")
    fun getAllMaterials(): Flow<List<StudyMaterial>>

    @Query("SELECT * FROM study_materials WHERE subjectId = :subjectId ORDER BY chapterNumber ASC, createdAt DESC")
    fun getMaterialsBySubject(subjectId: String): Flow<List<StudyMaterial>>

    @Query("SELECT * FROM study_materials WHERE subjectId = :subjectId AND chapterNumber = :chapterNumber ORDER BY createdAt DESC")
    fun getMaterialsByChapter(subjectId: String, chapterNumber: Int): Flow<List<StudyMaterial>>

    @Query("SELECT * FROM study_materials WHERE subjectId = 'OTHER' AND category = :category ORDER BY createdAt DESC")
    fun getOtherMaterialsByCategory(category: String): Flow<List<StudyMaterial>>

    @Query("SELECT * FROM study_materials WHERE id = :id LIMIT 1")
    suspend fun getMaterialById(id: Long): StudyMaterial?

    @Query("SELECT COUNT(*) FROM study_materials")
    fun getMaterialCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM study_materials WHERE materialType = 'PDF'")
    fun getPdfCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM study_materials WHERE materialType = 'NOTE'")
    fun getNoteCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterial(material: StudyMaterial): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(materials: List<StudyMaterial>)

    @Update
    suspend fun updateMaterial(material: StudyMaterial)

    @Delete
    suspend fun deleteMaterial(material: StudyMaterial)

    @Query("DELETE FROM study_materials WHERE id = :id")
    suspend fun deleteMaterialById(id: Long)

    @Query("SELECT COUNT(*) FROM study_materials")
    suspend fun getCountSync(): Int
}

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val status: ProjectStatus
)

enum class ProjectStatus {
    PLANNING, IN_PROGRESS, COMPLETED, ON_HOLD
}

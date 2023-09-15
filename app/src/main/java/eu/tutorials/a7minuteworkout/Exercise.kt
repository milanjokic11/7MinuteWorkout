package eu.tutorials.a7minuteworkout

class Exercise (
    private var id: Int,
    private var name: String,
    private var img: Int,
    private var isCompleted: Boolean = false,
    private var isSelected: Boolean = false
) {
    fun getID(): Int { return  id }
    fun setID(id: Int) { this.id = id}
    fun getName(): String { return  name }
    fun setName(name: String) { this.name = name}
    fun getIMG(): Int { return  img }
    fun setIMG(img: Int) { this.img = img}
    fun getIsCompleted(): Boolean { return  isCompleted }
    fun setIsCompleted(bool: Boolean) { this.isCompleted = bool }
    fun getIsSelected(): Boolean { return  isSelected }
    fun setIsSelected(bool: Boolean) { this.isSelected = bool }
}
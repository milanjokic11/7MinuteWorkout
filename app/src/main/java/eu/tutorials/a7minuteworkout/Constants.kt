package eu.tutorials.a7minuteworkout

object Constants {

    fun defaultExerciseList(): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        val jumpingJacks = Exercise(1, "Jumping Jacks", R.drawable.jumping_jacks)
        exerciseList.add(jumpingJacks)
        val dips = Exercise(2, "Dips", R.drawable.dips)
        exerciseList.add(dips)
        val lunge = Exercise(3, "Lunge", R.drawable.lunge)
        exerciseList.add(lunge)
        val plank = Exercise(4, "Plank", R.drawable.plank)
        exerciseList.add(plank)
        val pushUp = Exercise(5, "Push Up", R.drawable.push_up)
        exerciseList.add(pushUp)
        val highKnees = Exercise(6, "High Knees", R.drawable.high_knees)
        exerciseList.add(highKnees)
        val pushUpAndRotation = Exercise(7, "Push Up and Rotation", R.drawable.push_up_and_rotation)
        exerciseList.add(pushUpAndRotation)
        val sidePlank = Exercise(8, "Side Plank", R.drawable.side_plank)
        exerciseList.add(sidePlank)
        val sitUp = Exercise(9, "Sit Up", R.drawable.sit_up)
        exerciseList.add(sitUp)
        val squat = Exercise(10, "Squat", R.drawable.squat)
        exerciseList.add(squat)
        val stepChair = Exercise(11, "Chair Step", R.drawable.step_onto_chair)
        exerciseList.add(stepChair)
        val wallSit = Exercise(12, "Wall Sit", R.drawable.wall_sit)
        exerciseList.add(wallSit)

        return exerciseList
    }

}
package com.example.picturecardgallery.data

data class PictureCard(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String = ""
)

object PictureData {
    val sampleImages = listOf(
        PictureCard(
            id = 1,
            title = "Sunset Landscape",
            imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=300&fit=crop",
            description = "Beautiful sunset over mountains"
        ),
        PictureCard(
            id = 2,
            title = "Ocean View",
            imageUrl = "https://images.unsplash.com/photo-1505142468610-359e7d316be0?w=400&h=300&fit=crop",
            description = "Peaceful ocean waves"
        ),
        PictureCard(
            id = 3,
            title = "Forest Path",
            imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=400&h=300&fit=crop",
            description = "Misty forest trail"
        ),
        PictureCard(
            id = 4,
            title = "City Skyline",
            imageUrl = "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400&h=300&fit=crop",
            description = "Modern city at night"
        ),
        PictureCard(
            id = 5,
            title = "Mountain Lake",
            imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=300&fit=crop",
            description = "Crystal clear mountain lake"
        ),
        PictureCard(
            id = 6,
            title = "Desert Dunes",
            imageUrl = "https://images.unsplash.com/photo-1547036967-23d11aacaee0?w=400&h=300&fit=crop",
            description = "Rolling sand dunes"
        ),
        PictureCard(
            id = 7,
            title = "Cherry Blossoms",
            imageUrl = "https://images.unsplash.com/photo-1522383225653-ed111181a951?w=400&h=300&fit=crop",
            description = "Pink cherry blossoms in spring"
        ),
        PictureCard(
            id = 8,
            title = "Northern Lights",
            imageUrl = "https://images.unsplash.com/photo-1531366936337-7c912a4589a7?w=400&h=300&fit=crop",
            description = "Aurora borealis in the night sky"
        )
    )
}
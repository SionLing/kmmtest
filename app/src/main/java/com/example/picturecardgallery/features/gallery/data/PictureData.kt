package com.example.picturecardgallery.features.gallery.data

import kotlinx.serialization.Serializable

@Serializable
data class PictureCard(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String = "",
    val videoUrl: String = ""
)

object PictureData {
    val sampleImages = listOf(
        PictureCard(
            id = 1,
            title = "Sunset Landscape",
            imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=300&fit=crop",
            description = "Beautiful sunset over mountains",
            videoUrl = "https://www.youtube.com/shorts/nknL-8yAvwc"
        ),
        PictureCard(
            id = 2,
            title = "Ocean View",
            imageUrl = "https://images.unsplash.com/photo-1505142468610-359e7d316be0?w=400&h=300&fit=crop",
            description = "Peaceful ocean waves",
            videoUrl = "https://www.youtube.com/watch?v=BHACKCNDMW8"
        ),
        PictureCard(
            id = 3,
            title = "Forest Path",
            imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=400&h=300&fit=crop",
            description = "Misty forest trail",
            videoUrl = "https://www.youtube.com/watch?v=eKFTSSKCzWA"
        ),
        PictureCard(
            id = 4,
            title = "City Skyline",
            imageUrl = "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400&h=300&fit=crop",
            description = "Modern city at night",
            videoUrl = "https://www.youtube.com/watch?v=nluQ888CJpA"
        ),
        PictureCard(
            id = 5,
            title = "Mountain Lake",
            imageUrl = "https://images.unsplash.com/photo-1439066615861-d1af74d74000?w=400&h=300&fit=crop",
            description = "Crystal clear mountain lake",
            videoUrl = "https://www.youtube.com/watch?v=UfcAVejslrU"
        ),
        PictureCard(
            id = 6,
            title = "Desert Dunes",
            imageUrl = "https://images.unsplash.com/photo-1547036967-23d11aacaee0?w=400&h=300&fit=crop",
            description = "Rolling sand dunes",
            videoUrl = "https://www.youtube.com/watch?v=0E6EE_gQNJA"
        ),
        PictureCard(
            id = 7,
            title = "Cherry Blossoms",
            imageUrl = "https://images.unsplash.com/photo-1522383225653-ed111181a951?w=400&h=300&fit=crop",
            description = "Pink cherry blossoms in spring",
            videoUrl = "https://www.youtube.com/watch?v=nP1elMUEkL8"
        ),
        PictureCard(
            id = 8,
            title = "Northern Lights",
            imageUrl = "https://images.unsplash.com/photo-1531366936337-7c912a4589a7?w=400&h=300&fit=crop",
            description = "Aurora borealis in the night sky",
            videoUrl = "https://www.youtube.com/watch?v=fVsONlc3OUY"
        )
    )
}
package com.stakehabit.domain.model

import java.time.Instant

/**
 * A habit that the user wants to build or break.
 */
data class Habit(
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val frequency: Frequency = Frequency.DAILY,
    val targetCount: Int = 1, // e.g., "drink 8 glasses of water"
    val unit: String = "", // "glasses", "minutes", "pages"
    val createdAt: Instant = Instant.now(),
    val isActive: Boolean = true,
    val colorHex: String = "#FF6200EE",
    val iconName: String = "check_circle"
)

enum class Frequency {
    DAILY,
    WEEKLY,
    CUSTOM
}

/**
 * A single check-in for a habit on a given date.
 */
data class HabitCheckIn(
    val id: Long = 0,
    val habitId: Long,
    val date: String, // YYYY-MM-DD
    val count: Int = 1, // how many times completed
    val note: String = "",
    val proofType: ProofType? = null,
    val proofData: String? = null, // photo path, NFC tag ID, etc.
    val timestamp: Instant = Instant.now()
)

enum class ProofType {
    PHOTO,
    NFC,
    LOCATION,
    NONE
}

/**
 * A financial stake placed on a habit.
 */
data class Stake(
    val id: Long = 0,
    val habitId: Long,
    val amountSats: Long, // amount staked in satoshis
    val startDate: String, // YYYY-MM-DD
    val endDate: String?, // null = ongoing
    val failureDestination: FailureDestination,
    val paymentHash: String? = null, // Lightning invoice hash
    val isActive: Boolean = true,
    val createdAt: Instant = Instant.now()
)

data class FailureDestination(
    val type: DestinationType,
    // LN address, pubkey, etc.
    val address: String = "",
    // human-readable, e.g. "GiveDirectly"
    val label: String = ""
)

enum class DestinationType {
    CHARITY,
    BURN,
    FRIEND,
    COMMUNITY_POOL,
    TIME_LOCKED
}

/**
 * Nostr attestation for a completed habit.
 */
data class HabitAttestation(
    val habitId: Long,
    val date: String,
    val nostrEventId: String? = null,
    val relayUrls: List<String> = emptyList()
)

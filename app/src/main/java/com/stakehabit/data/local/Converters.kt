package com.stakehabit.data.local

import androidx.room.TypeConverter
import com.stakehabit.domain.model.DestinationType
import com.stakehabit.domain.model.Frequency
import com.stakehabit.domain.model.ProofType
import java.time.Instant

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun toTimestamp(instant: Instant?): Long? = instant?.toEpochMilli()

    @TypeConverter
    fun fromFrequency(value: String): Frequency = Frequency.valueOf(value)

    @TypeConverter
    fun toFrequency(frequency: Frequency): String = frequency.name

    @TypeConverter
    fun fromProofType(value: String?): ProofType? = value?.let { ProofType.valueOf(it) }

    @TypeConverter
    fun toProofType(proofType: ProofType?): String? = proofType?.name

    @TypeConverter
    fun fromDestinationType(value: String): DestinationType = DestinationType.valueOf(value)

    @TypeConverter
    fun toDestinationType(type: DestinationType): String = type.name
}

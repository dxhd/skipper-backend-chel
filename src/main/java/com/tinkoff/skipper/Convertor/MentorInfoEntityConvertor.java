package com.tinkoff.skipper.Convertor;

import com.tinkoff.skipper.DTO.MentorDTO;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
// FIXME: rename to builder
public class MentorInfoEntityConvertor {
    public static MentorInfoEntity convert(MentorDTO mentor ) {
        MentorInfoEntity mentorInfoEntity = new MentorInfoEntity();
        BeanUtils.copyProperties(mentor, mentorInfoEntity, "userId");
        // TODO: query student number
        return mentorInfoEntity;
    }
}

/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.util.sortutiltest;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.feilong.core.bean.ConvertUtil;
import com.feilong.test.User;

import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.core.util.SortUtil.sortByPropertyNamesValue;

public class SortUtilSortByPropertyNamesValueTest{

    /**
     * Test property comparator.
     */
    @Test
    public void testSortByPropertyNamesValueWithNullPropertyValue(){
        User u_null_id = new User((Long) null);
        User id12 = new User(12L);
        User id2 = new User(2L);
        User u_null = null;
        User id1 = new User(1L);

        List<User> list = toList(u_null_id, id12, id2, u_null, id1, u_null_id);
        sortByPropertyNamesValue(list, "id");
        assertThat(list, contains(u_null_id, u_null_id, id1, id2, id12, u_null));
    }

    /**
     * Test property comparator1.
     */
    @Test
    public void testSortByPropertyNamesValue(){
        User id12 = new User(12L, 18);
        User id2 = new User(2L, 36);
        User id5 = new User(5L, 22);
        User id1 = new User(1L, 8);
        List<User> list = toList(id12, id2, id5, id1);
        sortByPropertyNamesValue(list, "id");

        assertThat(list, contains(id1, id2, id5, id12));
    }

    @Test
    public void testSortByPropertyNamesValue2PropertyNames(){
        User id12_age18 = new User(12L, 18);
        User id1_age8 = new User(1L, 8);
        User id2_age30 = new User(2L, 30);
        User id2_age2 = new User(2L, 2);
        User id2_age36 = new User(2L, 36);
        List<User> list = toList(id12_age18, id2_age36, id2_age2, id2_age30, id1_age8);

        sortByPropertyNamesValue(list, "id", "age");
        assertThat(list, contains(id1_age8, id2_age2, id2_age30, id2_age36, id12_age18));
    }

    @Test
    public void testSortByPropertyNamesValueAgePropertyName(){
        User id12_age18 = new User(12L, 18);
        User id1_age8 = new User(1L, 8);
        User id2_age30 = new User(2L, 30);
        User id2_age2 = new User(2L, 2);
        User id2_age36 = new User(2L, 36);
        List<User> list = toList(id12_age18, id2_age36, id2_age2, id2_age30, id1_age8);

        sortByPropertyNamesValue(list, "age");

        assertThat(list, contains(id2_age2, id1_age8, id12_age18, id2_age30, id2_age36));
    }

    @Test
    public final void testSortByPropertyNamesValueNullList1PropertyName(){
        assertEquals(emptyList(), sortByPropertyNamesValue((List) null, "name"));
    }

    @Test
    public final void testSortByPropertyNamesValueNullList(){
        assertEquals(emptyList(), sortByPropertyNamesValue((List) null, "name", "age"));
    }

    @Test(expected = NullPointerException.class)
    public void testSortByPropertyNamesValueNullPropertyNames(){
        User id12_age18 = new User(12L, 18);
        User id1_age8 = new User(1L, 8);
        List<User> list = toList(id12_age18, id1_age8);
        sortByPropertyNamesValue(list, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortByPropertyNamesValueEmptyPropertyNames(){
        User id12_age18 = new User(12L, 18);
        User id1_age8 = new User(1L, 8);
        List<User> list = toList(id12_age18, id1_age8);
        sortByPropertyNamesValue(list, ConvertUtil.<String> toArray());
    }

    //    @Test(expected = IllegalArgumentException.class)
    //    public void testSortByPropertyNamesValueNullElementPropertyNames1(){
    //        User id12_age18 = new User(12L, 18);
    //        User id1_age8 = new User(1L, 8);
    //        List<User> list = toList(id12_age18, id1_age8);
    //        sortByPropertyNamesValue(list, ConvertUtil.<String> toArray("id", (String) null));
    //    }
}

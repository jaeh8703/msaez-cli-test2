<template>
    <v-card outlined>
        <v-card-title>
            Pet # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <Photo offline label="Photo" v-model="item.photo" :editMode="false" @change="change" />
            <div>
                <String label="Name" v-model="item.name" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Energy" v-model="item.energy" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Appearance" v-model="item.appearance" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Weight" v-model="item.weight" :editMode="editMode" @change="change" />
            </div>
            <Address offline label="Address" v-model="item.address" :editMode="false" @change="change" />
            <Status offline label="Status" v-model="item.status" :editMode="false" :key="false" @change="change" />
            <Type offline label="Type" v-model="item.type" :editMode="false" :key="false" @change="change" />
            <IllnessHistoryManager offline label="IllnessHistory" v-model="item.illnessHistory" :editMode="false" @change="change" />
            <div>
                <String label="Test123" v-model="item.test123" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'PetDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/pets/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>

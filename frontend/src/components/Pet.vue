<template>

    <v-card style="width:450px;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="deep-purple"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            Pet # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            Pet
        </v-card-title >

        <v-card-text>
            <Photo offline label="Photo" v-model="value.photo" :editMode="editMode" @change="change"/>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <Number label="Energy" v-model="value.energy" :editMode="editMode"/>
            <Number label="Appearance" v-model="value.appearance" :editMode="editMode"/>
            <Number label="Weight" v-model="value.weight" :editMode="editMode"/>
            <Address offline label="Address" v-model="value.address" :editMode="editMode" @change="change"/>
            <Status offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
            <Type offline label="Type" v-model="value.type" :editMode="editMode" @change="change"/>
            <IllnessHistoryManager offline label="IllnessHistory" v-model="value.illnessHistory" :editMode="editMode" @change="change"/>
            <String label="Test123" v-model="value.test123" :editMode="editMode"/>

        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
            >
                Cancel
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>                        
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="feed123"
            >
                Feed123
            </v-btn>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="groom"
            >
                Groom
            </v-btn>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="comb"
            >
                Comb
            </v-btn>
        </v-card-actions>

        <v-snackbar
                v-model="snackbar.status"
                :top="true"
                :timeout="snackbar.timeout"
                color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    import Photo from './vo/Photo.vue';
    import Address from './vo/Address.vue';

    export default {
        name: 'Pet',
        components:{
            Photo,
            Address,
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            childPath: String,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: ''
            },
        }),
        created(){
        },
        methods: {
            selectFile(){
            if(this.editMode == false) {
                return false;
            }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            if(this.childPath) {
                                temp = await axios.post(axios.fixUrl('/'+this.childPath), this.value)
                            } else {
                                temp = await axios.post(axios.fixUrl('/pets'), this.value)
                            }
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async feed123() {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links.feed.href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
            },
            async groom() {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links.groom.href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
            },
            async comb() {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links.comb.href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links..href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    this.snackbar.text = e
                }
            },
        },
    }
</script>


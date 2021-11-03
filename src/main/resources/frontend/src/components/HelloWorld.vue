<template>
  <div class="hello">
    <div v-for="article of data" :key="article">
      {{article.title}}
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {onMounted, ref} from "vue";
export default {
  name: 'HelloWorld',
  setup() {
    const data = ref([]);

    const getArticles = async () => {
      try {
        const data = await axios.get("http://localhost:25565/articles", {})
        console.log('service ' + data)
        console.log('service ' + data.data)
        return data.data
      } catch (e) {
        console.error(e);
        console.error(e.message);
        console.error(e.response);
        console.error(e.response.data);
        console.error(e.response.data.message);
        console.error(e.message);
        console.error(e.toString());
        return e;
      }
    }

    onMounted(async () => {
      data.value = await getArticles();
    })

    return {
      data
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

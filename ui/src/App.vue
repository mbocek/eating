<template>
  <v-app>
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant.sync="mini"
      permanent
      app
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-icon>person</v-icon>
        </v-list-item-avatar>

        <v-list-item-title>{{ user.name }}</v-list-item-title>
        <v-btn
          icon
          @click.stop="mini = !mini"
        >
          <v-icon>chevron_left</v-icon>
        </v-btn>
      </v-list-item>

      <v-divider></v-divider>
  
      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          :to="item.route"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app height="56">
        <v-toolbar-title class="headline">
          <span>Eation</span>
        </v-toolbar-title>
    </v-app-bar>

    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      drawer: true,
      user: {
        name: 'User Name',
      },
      items: [
          { title: 'Home', icon: 'home', route: '/' },
          { title: 'Food', icon: 'fastfood', route: 'food' },
          { title: 'About', icon: 'question_answer', route: 'about' }
      ],
      mini: true,
      right: null
    }
  }
}
</script>

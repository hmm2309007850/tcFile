import React, { Component } from 'react';

class Hmm extends Component {
  constructor(props){
    super(props);
    this.state = {
      list: [
        'study hh',
        'study mm',
        'study react'
      ]
    }
  }
  handleBtn(){
    this.setState({
      list: [...this.state.list, 'hello hh']
    })
  }
  render() {
    return (
      <div>
        <div>
          <input></input>
          <button onClick={this.handleBtn.bind(this)}>add</button>
        </div>
        <div>
          <ul>
            {
              this.state.list.map((item,index)=>{
                return <li key={index}>{item}</li>
              })
            }
          </ul>
        </div>
      </div>
    );
  }
}

export default Hmm;

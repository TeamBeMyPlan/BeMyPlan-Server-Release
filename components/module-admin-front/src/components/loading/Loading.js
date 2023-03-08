import styled from 'styled-components';
import Spinner from './loading.gif';

const Background = styled.div`
  position: absolute;
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;
  background: #ffffffb7;
  z-index: 999;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const LoadingText = styled.div`
  font: 1rem 'Noto Sans KR';
  text-align: center;
`;

export default () => {
  return (
    <Background>
      <LoadingText>업로드 중.</LoadingText>
      <img src={Spinner} alt="로딩중" width="5%" />
    </Background>
  );
};